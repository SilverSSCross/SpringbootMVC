package org.example.helloworldspringboot2026;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

interface GameRepository extends JpaRepository<Game, Integer> {
    public List<Game> findGamesByPlatformOrderByYearDesc(String platform);

    @Query("select g from Game g")
    public List<Game> findGames();

    @Query("select g from Game g where g.id = :id")
    public List<Game> findGames(@Param("id") Integer id);

    @Query("select distinct g.platform from Game g")
    public List<String> getPlatforms();
}
