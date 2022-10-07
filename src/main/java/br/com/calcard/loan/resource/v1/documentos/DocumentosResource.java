package br.com.calcard.loan.resource.v1.documentos;

import br.com.calcard.loan.dto.documento.TipoDocumentoResponse;
import br.com.calcard.loan.dto.documentos.DocumentosResponse;
import br.com.calcard.loan.service.documentos.DocumentosService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static br.com.calcard.loan.dto.RequestHeaders.IP;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/documentos")
public class DocumentosResource {

    private final DocumentosService documentosService;

    @GetMapping
    @Timed
    public DocumentosResponse buscaDocumentos(@RequestParam(value = "cpf") final String cpf,
                                              final HttpServletRequest servletRequest) {
        return documentosService.getFilesFromPerson(cpf, servletRequest.getHeader(IP));
    }

    @GetMapping("/tiposDocumentos")
    @Timed
    public TipoDocumentoResponse buscaTiposDocumentos() {

        return documentosService.buscaTiposDocumentos();
    }

}
