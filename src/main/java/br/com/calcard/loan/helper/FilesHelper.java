package br.com.calcard.loan.helper;

import br.com.calcard.api.common.exception.BusinessException;
import br.com.calcard.loan.exception.BusinessErrorException;
import br.com.calcard.loan.integration.store.dto.StorageDTO;
import br.com.calcard.loan.integration.store.dto.StoreDTO;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import static br.com.calcard.loan.dto.ErrorCode.STORAGE_NAO_ENCONTRADO;
import static java.util.Optional.ofNullable;
import static org.apache.commons.codec.binary.Base64.encodeBase64String;

@Slf4j
@RequiredArgsConstructor
@Component
public class FilesHelper {
    //TODO Rever alternativas para substituir essa lib antiga (jcifs-krb5-jdk7) que pode dar problemas futuros de incompatibilidade.

    public static SmbFile[] getAllFilesFromNetwork(StoreDTO storeDTO, String fileName) {
        try {
            return getSmbFile(storeDTO).listFiles("*" + fileName + "*");
        } catch (SmbException e) {
            log.info(e.getMessage(), e);
            return new SmbFile[0];
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new SmbFile[0];
        }
    }

    public static String toBase64(InputStream image) throws BusinessException {
        try {
            if (image == null) {
                return null;
            }
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(ImageIO.read(image), "jpg", out);
            byte[] bytes = out.toByteArray();
            String base64 = encodeBase64String(bytes);
            out.close();
            return "data:image/jpeg" + ";base64," + base64;
        } catch (IOException e) {
            throw new BusinessException("Não foi possível converter a miniatura em base64", e);
        }
    }

    private static SmbFile getSmbFile(StoreDTO storeDTO) throws MalformedURLException {
        StorageDTO storage = ofNullable(storeDTO.getStorage())
                                .orElseThrow(() -> new BusinessErrorException(STORAGE_NAO_ENCONTRADO, "Storage não encontrado"));
        NtlmPasswordAuthentication auth =
                new NtlmPasswordAuthentication("", storage.getUsername(), storage.getPassword());
        return new SmbFile(storage.getPath(), auth);
    }
}
