package com.zekademi.strongprettyhomes.repository;

import com.zekademi.strongprettyhomes.domain.Property;
import com.zekademi.strongprettyhomes.domain.Review;
import com.zekademi.strongprettyhomes.domain.User;
import com.zekademi.strongprettyhomes.dto.ReviewDTO;
import com.zekademi.strongprettyhomes.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<ReviewDTO> findAllByPropertyId(Long property);

    Optional<ReviewDTO> findByIdAndUserId(Long id, User user);

    Optional<ReviewDTO> findByIdOrderById(Long id) throws ResourceNotFoundException;
    
    void deleteReviewByIdAndUser(Long id,User userId);
    
    @Query("SELECT new com.zekademi.strongprettyhomes.dto.ReviewDTO(r) FROM Review r WHERE r.user.id = ?1 and r.id=?2")
    Optional<ReviewDTO> findReviews(Long userId, Long id);
    
    @Query("SELECT new com.zekademi.strongprettyhomes.dto.ReviewDTO(r) FROM Review r")
    List<ReviewDTO> findAllReview();
}
