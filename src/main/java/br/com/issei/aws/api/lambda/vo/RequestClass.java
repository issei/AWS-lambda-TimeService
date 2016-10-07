package br.com.issei.aws.api.lambda.vo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;

import br.com.issei.time.entity.Data;
import br.com.issei.time.entity.Expediente;
import br.com.issei.time.entity.ExpedienteExtraordinario;

/*
 * Exemplo de request JSON
 * <code>
{
	"dataInicio": "2016-09-29",
	"dataFim": "2016-10-10",
	"dataPattern" : "yyyy-MM-dd",
	"horaInicioExpedienteDefault" : "09:00",
	"horaFimExpedienteDefault" :  "18:00",
	"timePattern" : "HH:mm",
	"expedienteExtraordinario" : [{
		"data": "2016-09-29",
		"dataPattern" : "yyyy-MM-dd",
		"horaInicioExpediente" : "09:00",
		"horaFimExpediente" :  "18:00",
		"timePattern" : "HH:mm"
	},
	{
		"data": "2016-09-30",
		"dataPattern" : "yyyy-MM-dd",
		"horaInicioExpediente" : "09:00",
		"horaFimExpediente" :  "18:00",
		"timePattern" : "HH:mm"
	}],
	"diasNaoUteis": [{
		"data": "2016-10-01",
		"dataPattern" : "yyyy-MM-dd"
	},
	{
		"data": "2016-10-02",
		"dataPattern" : "yyyy-MM-dd"
	}]	,
	"diasDetermindadosUteis" : [{
		"data": "2016-10-08",
		"dataPattern" : "yyyy-MM-dd"
	},
	{
		"data": "2016-10-09",
		"dataPattern" : "yyyy-MM-dd"
	}]		
}
 * </code>
 */
public class RequestClass {

	private String dataInicio;
	private String dataFim;
	private String dataPattern = "yyyy-MM-dd";//default value init
	private String horaInicioExpedienteDefault = "00:00:00";//default value init
	private String horaFimExpedienteDefault = "23:59:59";//default value init
	private String timePattern = "HH:mm:ss";
	private List<ExpedienteExtraordinario> expedientesExtraordinarios;
	private List<Data> diasNaoUteis;
	private List<Data> diasDetermindadosUteis;
	
	
	public DateTime getDataInicio()
	{
		return DateTimeFormat.forPattern(this.dataPattern).parseDateTime(this.dataInicio);
	}
	
	public DateTime getDataFim()
	{
		return DateTimeFormat.forPattern(this.dataPattern).parseDateTime(this.dataFim);
	}

	public Expediente getExpediente() {
		return new Expediente(
				LocalTime.parse(horaInicioExpedienteDefault, DateTimeFormat.forPattern(timePattern)),
				LocalTime.parse(horaFimExpedienteDefault, DateTimeFormat.forPattern(timePattern)));
	}
	
	public HashMap<LocalDate,Expediente> getExpedientesExtraordinarios()
	{
		HashMap<LocalDate,Expediente> result = new HashMap<LocalDate,Expediente>();
		if(this.expedientesExtraordinarios!=null)
		for(ExpedienteExtraordinario ex : this.expedientesExtraordinarios){
			result.put(ex.getData(), ex.getExpediente());
		}
		return result;
	}
	
	public HashSet<LocalDate> getDiasNaoUties()
	{
		HashSet<LocalDate> result = new HashSet<LocalDate>();
		if(this.diasNaoUteis!=null)
		for(Data dia: this.diasNaoUteis)
		{
			result.add(dia.getData());
		}
		return result;
		
	}
	
	public HashSet<LocalDate> getDiasDetermindadosUteis()
	{
		HashSet<LocalDate> result = new HashSet<LocalDate>();
		if(this.diasDetermindadosUteis!=null)
		for(Data dia: this.diasDetermindadosUteis)
		{
			result.add(dia.getData());
		}
		return result;
		
	}

	public String getHoraInicioExpedienteDefault() {
		return horaInicioExpedienteDefault;
	}

	public void setHoraInicioExpedienteDefault(String horaInicioExpedienteDefault) {
		this.horaInicioExpedienteDefault = horaInicioExpedienteDefault;
	}

	public String getHoraFimExpedienteDefault() {
		return horaFimExpedienteDefault;
	}

	public void setHoraFimExpedienteDefault(String horaFimExpedienteDefault) {
		this.horaFimExpedienteDefault = horaFimExpedienteDefault;
	}

	public String getTimePattern() {
		return timePattern;
	}

	public void setTimePattern(String timePattern) {
		this.timePattern = timePattern;
	}

	public List<Data> getDiasNaoUteis() {
		return diasNaoUteis;
	}

	public void setDiasNaoUteis(List<Data> diasNaoUteis) {
		this.diasNaoUteis = diasNaoUteis;
	}

	public void setExpedientesExtraordinarios(List<ExpedienteExtraordinario> expedientesExtraordinarios) {
		this.expedientesExtraordinarios = expedientesExtraordinarios;
	}

	public void setDiasDetermindadosUteis(List<Data> diasDetermindadosUteis) {
		this.diasDetermindadosUteis = diasDetermindadosUteis;
	}

	public String getDataPattern() {
		return dataPattern;
	}

	public void setDataPattern(String dataPattern) {
		this.dataPattern = dataPattern;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

}
