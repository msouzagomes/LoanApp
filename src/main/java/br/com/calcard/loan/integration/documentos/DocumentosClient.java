package br.com.calcard.loan.integration.documentos;

import br.com.calcard.loan.dto.biometria.BiometriaRequest;
import br.com.calcard.loan.dto.documento.TipoDocumentoResponse;
import br.com.calcard.loan.integration.config.FeignIntegrationConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "DocumentosClient", url = "${api.path.loan.host}${api.path.loan.basePath}", configuration = {
    FeignIntegrationConfig.class})
public interface DocumentosClient {

    @GetMapping(value = "${api.path.loan.documentos.basePath}${api.path.loan.documentos.tiposDocumentos}",
        consumes = MediaType.APPLICATION_JSON_VALUE)
    TipoDocumentoResponse buscaTiposDocumentos();

    @PostMapping(value = "${api.path.loan.documentos.basePath}${api.path.loan.documentos.salvarDocumentos}",
        consumes = MediaType.APPLICATION_JSON_VALUE)
    void salvarDocumentos(BiometriaRequest request);

}
