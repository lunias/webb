package edu.capella.webb.peoplesoft.server.util;

import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.capella.webb.peoplesoft.server.domain.learnerInfo.PSLearnerInfoV;
import edu.capella.webb.peoplesoft.server.repository.PSLearnerInfoVRepository;

@Component
public class H2DataInitializer implements InitializingBean {

	@Autowired
	PSLearnerInfoVRepository psLearnerInfoVRepository;
	
	@Override
	public void afterPropertiesSet() throws Exception {

		List<PSLearnerInfoV> psLearnerInfoVRows = Arrays
				.asList(new PSLearnerInfoV[] {						
						
						new PSLearnerInfoV("1000000", "CM", "7080", "FC",
								"CRS", new DateTime(
										"2012-02-03T14:15:00.000+08:00"),
								new DateTime("2012-01-02T12:11:00.000+08:00"),
								new DateTime("2013-04-03T01:22:00.000+08:00"),
								new DateTime("2012-06-03T14:15:00.000+08:00"),
								new DateTime("2012-08-03T14:15:00.000+08:00"),
								new DateTime("2013-02-03T05:15:00.000+08:00"),
								new DateTime("2013-02-03T06:15:00.000+08:00"),
								"Statistical Research Technique", false, false,
								12, true, new DateTime(
										"2012-02-03T14:15:00.000+08:00"),
								new DateTime("2012-02-03T14:15:00.000+08:00"),
								"CU_CONVERSION"),
								
						new PSLearnerInfoV("1000001", "PR", "7090", "BCM",
								"CTR", new DateTime(
										"2012-02-03T14:15:00.000+08:00"),
								new DateTime("2012-01-02T12:11:00.000+08:00"),
								new DateTime("2013-04-03T01:22:00.000+08:00"),
								new DateTime("2012-06-03T14:15:00.000+08:00"),
								new DateTime("2012-08-03T14:15:00.000+08:00"),
								new DateTime("2013-02-03T05:15:00.000+08:00"),
								new DateTime("2013-02-03T06:15:00.000+08:00"),
								"Marketing", true, true,
								2, false, new DateTime(
										"2012-02-03T14:15:00.000+08:00"),
								new DateTime("2012-02-03T14:15:00.000+08:00"),
								"CU_CONVERSION") 
						});
		
		psLearnerInfoVRepository.save(psLearnerInfoVRows);
	}

}
