package edu.capella.webb.peoplesoft.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.capella.webb.peoplesoft.server.domain.learnerInfo.PSLearnerInfoV;
import edu.capella.webb.peoplesoft.server.domain.learnerInfo.PSLearnerInfoVPK;

public interface PSLearnerInfoVRepository extends JpaRepository<PSLearnerInfoV, PSLearnerInfoVPK> {

	List<PSLearnerInfoV> findByEmployeeId(String employeeId);
	
	PSLearnerInfoV findFirstByEmployeeId(String employeeId);	
}
