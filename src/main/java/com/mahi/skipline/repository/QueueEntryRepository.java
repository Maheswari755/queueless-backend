/*package com.mahi.skipline.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mahi.skipline.entity.QueueEntry;

public interface QueueEntryRepository
extends JpaRepository<QueueEntry,Long>{

    List<QueueEntry> findByQueue_IdAndStatus(
        Long id,
        String status
    );

    List<QueueEntry> findByQueue_Id(Long id);

    List<QueueEntry> findAllByOrderByIdDesc();

}*/

package com.mahi.skipline.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mahi.skipline.entity.QueueEntry;

public interface QueueEntryRepository
extends JpaRepository<QueueEntry,Long>{

    List<QueueEntry> findByQueue_IdAndStatus(
        Long id,
        String status
    );

    List<QueueEntry> findByQueue_Id(Long id);

    List<QueueEntry> findAllByOrderByIdDesc();

    List<QueueEntry> findByUser_IdAndQueue_IdAndStatusIn(
        Long userId,
        Long queueId,
        List<String> statuses
    );

}