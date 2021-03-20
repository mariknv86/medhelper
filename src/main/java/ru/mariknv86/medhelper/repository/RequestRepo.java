package ru.mariknv86.medhelper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.mariknv86.medhelper.model.Request;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepo extends JpaRepository<Request, Long> {

    @Query("FROM Request r where r.question LIKE %:question%")
    List<Request> findRequestByQuestion(@Param("question") String question);

}
