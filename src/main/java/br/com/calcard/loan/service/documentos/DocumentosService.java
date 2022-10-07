package br.com.calcard.loan.service.documentos;

import br.com.calcard.api.common.exception.BusinessException;
import br.com.calcard.loan.dto.documentos.Documento;
import br.com.calcard.loan.dto.documentos.DocumentosResponse;
import br.com.calcard.loan.exception.BusinessErrorException;
import br.com.calcard.loan.helper.FilesHelper;
import br.com.calcard.loan.helper.MessageHelper;
import br.com.calcard.loan.integration.store.StoreClient;
import br.com.calcard.loan.integration.store.dto.StoreDTO;
import br.com.calcard.loan.validator.CpfValidator;
import jcifs.smb.SmbFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import br.com.calcard.loan.integration.documentos.DocumentosClient;
import br.com.calcard.loan.dto.documento.TipoDocumentoResponse;
import br.com.calcard.loan.exception.BusinessErrorException;

import static br.com.calcard.loan.dto.ErrorCode.*;
import static java.util.Optional.ofNullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentosService {

    private final StoreClient storeClient;
    private final MessageHelper messageHelper;
    private final CpfValidator cpfValidator;
    private final DocumentosClient documentosClient;

    public DocumentosResponse getFilesFromPerson(String cpf, String ip){

        cpfValidator.validar(cpf);

        log.info("Buscando documentos para o cpf {}", sha256Hex(cpf));

        try {
            StoreDTO store = ofNullable(storeClient.getStoreStorage(ip))
                                .orElseThrow(() -> new BusinessErrorException(LOJA_NAO_ENCONTRADA,
                                        messageHelper.get(LOJA_NAO_ENCONTRADA)));
            SmbFile[] files = ofNullable(FilesHelper.getAllFilesFromNetwork(store, cpf))
                                .orElseThrow(() -> new BusinessErrorException(DOCUMENTOS_NAO_ENCONTRADOS_NO_DIRETORIO,
                                    messageHelper.get(DOCUMENTOS_NAO_ENCONTRADOS_NO_DIRETORIO)));

            List<Documento> resultFiles = new ArrayList<>();

            for (SmbFile file : files) {
                String base64 = FilesHelper.toBase64(file.getInputStream());
                resultFiles.add(Documento.builder()
                                .name(file.getName())
                                .base64(base64)
                                .build());
                file.getInputStream().close();
            }

            resultFiles.forEach(documento -> log.info("Documentos encontrados {}", documento.getName()));
            Optional.of(resultFiles).filter(ObjectUtils::isNotEmpty)
                    .orElseThrow(() -> new BusinessErrorException(DOCUMENTOS_NAO_ENCONTRADOS_NO_DIRETORIO,
                            messageHelper.get(DOCUMENTOS_NAO_ENCONTRADOS_NO_DIRETORIO)));

            return DocumentosResponse
                    .builder()
                    .documentos(resultFiles)
                    .build();

        } catch (BusinessException | IOException e) {
            log.error(messageHelper.get(ERRO_AO_BUSCAR_DOCUMENTO_NO_DIRETORIO), e);
            throw new BusinessErrorException(ERRO_AO_BUSCAR_DOCUMENTO_NO_DIRETORIO, e.getMessage());
        }
    }

    public TipoDocumentoResponse buscaTiposDocumentos() {

        log.info("Buscando tipos de documentos.");

        return ofNullable(documentosClient.buscaTiposDocumentos())
                .filter(ObjectUtils::isNotEmpty)
                .orElseThrow(() -> new BusinessErrorException(TIPOS_DOCUMENTOS_NAO_ENCONTRADOS,
                        messageHelper.get(TIPOS_DOCUMENTOS_NAO_ENCONTRADOS)));
    }
}
