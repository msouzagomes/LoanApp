package br.com.calcard.loan.service.biometria;

import br.com.calcard.loan.annotation.Auditoria;
import br.com.calcard.loan.dto.biometria.BiometriaRequest;
import br.com.calcard.loan.dto.biometria.BiometriaResponse;
import br.com.calcard.loan.integration.documentos.DocumentosClient;
import br.com.calcard.loan.validator.CpfValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

@Service
@Slf4j
@RequiredArgsConstructor
public class BiometriaService {

    private final CpfValidator cpfValidator;
    private final DocumentosClient documentosClient;

    @Auditoria
    public BiometriaResponse iniciarBiometria(final BiometriaRequest request) {

        log.info("Iniciando biometria para o cpf {}", sha256Hex(request.getCpf()));

        cpfValidator.validar(request.getCpf());

        documentosClient.salvarDocumentos(request);

        return BiometriaResponse.builder().build();
    }

}
