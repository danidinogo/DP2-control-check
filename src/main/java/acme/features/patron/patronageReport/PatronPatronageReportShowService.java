package acme.features.patron.patronageReport;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronagereport.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronPatronageReportShowService implements AbstractShowService<Patron, PatronageReport>{
	
	
	
	
	@Autowired
	protected PatronPatronageReportRepository repository;
	
	
 
	
	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;
		
		int id = request.getPrincipal().getActiveRoleId();
		Collection<PatronageReport> patronageReports = this.repository.findAllPatronageReportsByPatronId(id);
		int patronage_id = request.getModel().getInteger("id");
		PatronageReport patronageReport = this.repository.findPatronageReportById(patronage_id);
		return patronageReports.contains(patronageReport);
	}
	
	
	@Override
	public PatronageReport findOne(final Request<PatronageReport> request) {
		assert request != null;
		
		PatronageReport result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findPatronageReportById(id);
		
		
		return result;
	}
	
	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		
		request.unbind(entity, model,  "seqNumber", "createdAt", "memorandum", "link");
	}
	
}
