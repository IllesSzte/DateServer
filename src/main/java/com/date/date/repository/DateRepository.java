package com.date.date.repository;

import com.date.date.model.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DateRepository extends JpaRepository<Date, Integer> {
    /**
     * If param has no input then i request all by default
     * @param price_in under price.
     * @param place_in
     * @param crowded_in
     * @param activity_in
     * @param season_in
     * @param duration_in
     * @param daytime_in
     * @return
     */
    @Query(value = "CALL search_dates(:owner_in,:partner_in,:price_in, :place_in, :crowded_in, :activity_in, :season_in, :duration_in, :daytime_in)",
            nativeQuery = true)
    List<Date> searchDates(@Param("owner_in") Integer owner_in,
                           @Param("partner_in") Integer partner_in,
                           @Param("price_in") Integer price_in,
                          @Param("place_in") String place_in,
                          @Param("crowded_in") String crowded_in,
                          @Param("activity_in") String activity_in,
                          @Param("season_in") String season_in,
                          @Param("duration_in") String duration_in,
                          @Param("daytime_in") String daytime_in);

    void deleteById(int id);

    List<Date> findDateByOwner(int ownerId);
}
