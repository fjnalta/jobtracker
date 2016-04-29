package net.greenbeansit.jobtracker.backend.services.activityreport;

import org.springframework.data.repository.CrudRepository;

public interface ActivityReportRepository extends CrudRepository<ActivityReportEntity, Long> {

	//TODO: Vervollständigen, checken wohin das gehört
	ActivityReportEntity findByLastname(String lastname);
}
