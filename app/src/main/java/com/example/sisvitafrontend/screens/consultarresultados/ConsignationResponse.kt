package com.example.sisvitafrontend.screens.consultarresultados

/*
@JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp date;
    private String observation;
    private String fundament;
    private String nameSpecialist;
    private String nameDiagnosis;
    private String fundamentDiagnosis;
    private String nameTreatment;
    private String fundamentTreatment;
 */

data class ConsignationResponse(
    val id: Long,
    val date: String,
    val observation: String,
    val fundament: String,
    val nameSpecialist: String,
    val nameDiagnosis: String,
    val fundamentDiagnosis: String,
    val nameTreatment: String,
    val fundamentTreatment: String
)
