package jp.co.pos.apidemo.repositories;

import jp.co.pos.apidemo.entities.POSEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface POSRepository extends JpaRepository<POSEntity,Long> {

    @Query("SELECT dateTime, sales, points FROM POSEntity WHERE dateTime >= :startDate AND dateTime <= :endDate ORDER BY dateTime ASC")
    public List<POSEntity> getHistory(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);
}
