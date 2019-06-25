package br.com.ame.gamestarwars.services.starwars;

import br.com.ame.gamestarwars.vo.StarWarsVO;
import reactor.core.publisher.Flux;

public interface IStarWarsService {
	
	Flux<StarWarsVO> getPlanets();
}
