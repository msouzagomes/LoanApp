package br.com.calcard.loan.integration.account.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@With
@JsonDeserialize(builder = AccountDetailPierlabsDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class AccountDetailPierlabsDTO {

    @JsonProperty(value = "id")
    Long id;
    @JsonProperty(value = "nomeOrigemComercial")
    String comercialOriginName;
    @JsonProperty(value = "idFantasiaBasica")
    Long groupCode;
    @JsonProperty(value = "idStatusConta")
    Integer idStatus;
    @JsonProperty(value = "statusConta")
    String status;
    @JsonProperty(value = "diaVencimento")
    Integer dueDay;
    @JsonProperty(value = "melhorDiaCompra")
    Integer bestDayToBuy;
    @JsonProperty(value = "dataStatusConta")
    LocalDate accountStatusDate;
    @JsonProperty(value = "dataCadastro")
    LocalDate dtRegistration;
    @JsonProperty(value = "dataUltimaAlteracaoVencimento")
    LocalDate dtLastPaymentDateChange;
    @JsonProperty(value = "dataHoraUltimaCompra")
    LocalDateTime dtLastBuy;
    @JsonProperty(value = "valorRenda")
    BigDecimal incomeValue;
    @JsonProperty(value = "formaEnvioFatura")
    String invoiceTypeEnum;
    @JsonProperty(value = "saldoAtualFinal")
    BigDecimal currentBalance;
    @JsonProperty(value = "diasAtraso")
    Integer daysLate;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {}
}
