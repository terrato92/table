package terrato.springframework.service;

import reactor.core.publisher.Mono;
import terrato.springframework.domain.BalanceOfMatches;

/**
 * Created by onenight on 2018-03-04.
 */
public interface WinService {

    Mono<BalanceOfMatches> winMatch(String idTeam);
}
