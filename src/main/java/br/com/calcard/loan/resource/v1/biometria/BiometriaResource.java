package br.com.calcard.loan.resource.v1.biometria;

import br.com.calcard.loan.dto.biometria.BiometriaRequest;
import br.com.calcard.loan.dto.biometria.BiometriaResponse;
import br.com.calcard.loan.service.biometria.BiometriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/biometria")
public class BiometriaResource {

    private final BiometriaService biometriaService;

    @PostMapping(value = "/iniciarBiometria")
    public BiometriaResponse iniciarBiometria(@RequestBody @Validated final BiometriaRequest request) {
        return biometriaService.iniciarBiometria(request);
    }
}
