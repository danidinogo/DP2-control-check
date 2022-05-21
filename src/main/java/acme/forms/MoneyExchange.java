package acme.forms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

import acme.framework.datatypes.Money;

public class MoneyExchange {

	@NotNull
	@Valid
	public Money source;

	@NotBlank
	public String targetCurrency;
	
	private final String apiUrl = "https://v6.exchangerate-api.com/v6/9a03b319bdec3db5ab97a9bb/pair";

	public MoneyExchange(final Money source, final String target) {
		this.source = source;
		this.targetCurrency = target;
	}
	
	private String currencyPairUrl() {
		return this.apiUrl + "/" + this.source.getCurrency() + "/" + this.targetCurrency + "/" + this.source.getAmount();
	}
	
	public String getExchange() {
		final String url_str = this.currencyPairUrl();
		
		Double conversion = 0.0;

		try {
			// Making Request
			final URL url = new URL(url_str);
			final HttpURLConnection request = (HttpURLConnection) url.openConnection();
			request.setRequestMethod("GET");
			
			request.connect();
			
			final JsonParser springParser = JsonParserFactory.getJsonParser();
			
			final BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String inputLine;
			final StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();
			
			request.disconnect();
			
			String jsonRes = content.toString();
			
			jsonRes = jsonRes.replace("{", "");
			jsonRes = jsonRes.replace("}", "");
			final String[] values = jsonRes.split(",");
			
			final String[] conv = values[values.length - 1].split(":");
			
			conversion = Double.parseDouble(conv[conv.length - 1]);
			
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return this.targetCurrency + " " + conversion.toString();
	}
	
}
