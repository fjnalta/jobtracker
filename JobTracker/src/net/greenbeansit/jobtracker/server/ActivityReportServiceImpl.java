package net.greenbeansit.jobtracker.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;

import com.google.gwt.dev.util.collect.HashMap;

import net.greenbeansit.jobtracker.shared.ActivityReport;
import net.greenbeansit.jobtracker.shared.ActivityReportService;

public class ActivityReportServiceImpl implements ActivityReportService {
	
	private Long employeeId;
	private Map<Long, ActivityReport> reportMap;
	
	public ActivityReportServiceImpl(Long employeeId)
	{
		this.employeeId = employeeId;
		reportMap = new HashMap<Long, ActivityReport>();
	}

	@Override
	public ActivityReport[] getAllReports() {
		List<ActivityReport> list = new ArrayList<ActivityReport>();	
		for(Long l : reportMap.keySet())
		{
			ActivityReport report = reportMap.get(l);
			if(report.getAuthor() == employeeId)
				list.add(report);
		}
		return (ActivityReport[]) list.toArray();
	}

	@Override
	public ActivityReport getReport(Long reportId) {
		if(reportMap.containsKey(reportId))
		{
			return reportMap.get(reportId);
		}
		throw new NotFoundException();
	}

	@Override
	public ActivityReport[] getReportPeriod(Long employeeId, String from, String to) {
		
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActivityReport createReport(ActivityReport report) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActivityReport updateReport(ActivityReport report) {
		// TODO Auto-generated method stub
		return null;
	}

}
