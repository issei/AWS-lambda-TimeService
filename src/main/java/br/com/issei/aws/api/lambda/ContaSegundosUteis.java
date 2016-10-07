package br.com.issei.aws.api.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import br.com.issei.aws.api.lambda.vo.RequestClass;
import br.com.issei.aws.api.lambda.vo.ResponseClass;
import br.com.issei.time.util.TimeUtil;

/**
 * //Requisição simples
{
	"dataInicio": "2016-09-29",
	"dataFim": "2016-10-10"
}

//Requisição completa
{
	"dataInicio": "2016-09-29",
	"dataFim": "2016-10-10",
	"dataPattern" : "yyyy-MM-dd",
	"horaInicioExpedienteDefault" : "09:00",
	"horaFimExpedienteDefault" :  "18:00",
	"timePattern" : "HH:mm",
	
	"expedienteExtraordinarios" : [{
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



//Exemplo Retorno esperado
{
	"segundos" : "999999"
}
 * @author myokoyama
 *
 */
public class ContaSegundosUteis implements RequestHandler<RequestClass, ResponseClass> {

	@Override
	public ResponseClass handleRequest(RequestClass request, Context context) {
		TimeUtil util = new TimeUtil(request.getExpediente(),request.getDiasNaoUties(),request.getDiasDetermindadosUteis(),request.getExpedientesExtraordinarios());
		if(request.getDataInicio() ==null || request.getDataFim()==null) 
			throw new RuntimeException("DataInicio e DataFim são obritórios o preenchimento");
		ResponseClass response = new ResponseClass(util.getSegundosUteisEntre(request.getDataInicio(), request.getDataFim()).getSeconds());
		return response;
	}

}
