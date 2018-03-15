package terrato.springframework.service.implementation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import terrato.springframework.domain.League;
import terrato.springframework.domain.Team;
import terrato.springframework.exception.NotFoundException;
import terrato.springframework.repository.LeagueRepository;
import terrato.springframework.service.LeagueService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by onenight on 2018-03-03.
 */
@Slf4j
@Service
public class LeagueServiceImpl implements LeagueService {


    LeagueRepository leagueRepository;

    public LeagueServiceImpl(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    @Override
    public Set<League> getLeagues() {
        Set<League> leagues = new HashSet<>();
        leagueRepository.findAll().iterator().forEachRemaining(leagues::add);
        return leagues;
    }

    @Override
    public Set<Team> showLeagueTeams(Long idLeague) {
        Optional<League> leagueOptional = Optional.ofNullable(leagueRepository.findOne(idLeague));

        if (leagueOptional.isPresent()) {
            return leagueOptional.get().getTeams();
        } else {
            throw new NotFoundException("I can't find league with id: " + idLeague);
        }
    }

    @Override
    @Transactional
    public League getLeagueById(Long idLeague) {
        Optional<League> leagueOptional = Optional.ofNullable(leagueRepository.findOne(idLeague));

        if (!leagueOptional.isPresent()) {
            throw new NotFoundException("I can't find league with id: " + idLeague);
        } else {
            return leagueOptional.get();

        }
    }


    @Override
    @Transactional
    public League saveLeague(League league) {
        return leagueRepository.save(league);
    }


    @Override
    public void deleteLeagueById(Long idLeague) {
        Optional<League> leagueOptional = Optional.ofNullable(leagueRepository.findOne(idLeague));

        if (leagueOptional.isPresent()) {
            League league1 = leagueOptional.get();
            leagueRepository.delete(league1);
        } else {
            throw new NotFoundException("I can't find league.");
        }
    }


}