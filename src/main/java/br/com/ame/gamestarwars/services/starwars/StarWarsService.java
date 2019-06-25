package br.com.ame.gamestarwars.services.starwars;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.ame.gamestarwars.vo.StarWarsVO;
import reactor.core.publisher.Flux;

@Service
@Qualifier("starwars")
public class StarWarsService implements IStarWarsService {

	private String baseUrl = "https://swapi.co/api/planets";
	private static final String USER_AGENT = "Spring 5 WebClient";
	private static final String APPLICATION_JSON = "application/json";
	private final WebClient webClient;

	public StarWarsService() {
		this.webClient = WebClient
				.builder()
				.baseUrl(baseUrl)
					.defaultHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON)
					.defaultHeader(HttpHeaders.USER_AGENT, USER_AGENT)
					.defaultHeader(HttpHeaders.ACCEPT, APPLICATION_JSON)
				.build();
	}

	@Override
	public Flux<StarWarsVO> getPlanets() {
		return this.webClient
				.get()
				.exchange()
				.flatMapMany(res -> res.bodyToFlux(StarWarsVO.class));
	}

}
