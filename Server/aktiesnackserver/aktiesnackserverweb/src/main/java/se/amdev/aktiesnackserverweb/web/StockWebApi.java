package se.amdev.aktiesnackserverweb.web;

import static se.amdev.aktiesnackserverweb.parser.ModelParser.parseCollectionStock;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import se.amdev.aktiesnackserverdata.model.StockData;
import se.amdev.aktiesnackserverweb.model.StockWeb;
import se.amdev.aktiesnackserverweb.service.WebService;

@Path("/stocks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StockWebApi {

	private WebService service;
	public static int activated = 0;

	@Context
	private UriBuilder uriBuilder;

	@Context
	private UriInfo uriInfo;

	@Context
	private HttpHeaders headers;

	public StockWebApi() {
		this.service = new WebService();
	}

	@GET
	public Response getStock(@QueryParam("sn") String stockName) {
		if (stockName != null) {
			StockData stockData = service.findStockByStockName(stockName);
			if (stockData != null) {
				return Response.ok(new StockWeb(stockData)).build();
			}
			else {
				return Response.status(Status.NO_CONTENT).build();
			}
		}
		else {
			Collection<StockWeb> stocks = parseCollectionStock(service.findAllStocks());
			if (stocks.isEmpty()) {
				return Response.status(Status.NO_CONTENT).build();
			}

			GenericEntity<Collection<StockWeb>> entity = new GenericEntity<Collection<StockWeb>>(stocks)
			{
			};

			return Response.ok(entity).build();
		}
	}
	
//	@GET
//	@Path("/activate")
//	public Response startUpdate() throws ParseException{
//		if(activated == 1){
//			return Response.status(Status.BAD_REQUEST).build();
//		} 
//		else if(activated == 0){
//			StockDownloaderMain.stockLoader();
//			return Response.status(Status.ACCEPTED).build();
//		}
//		return Response.status(Status.NO_CONTENT).build();
//	}
}
