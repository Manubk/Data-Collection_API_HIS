package com.dc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.dto.BankDto;
import com.dc.dto.ChildrenDto;
import com.dc.dto.ChildrenRequestDto;
import com.dc.dto.DcSummery;
import com.dc.dto.EducationDto;
import com.dc.dto.IncomeDto;
import com.dc.dto.PlanSelectionDto;
import com.dc.dto.PlanSelectionRequestDto;
import com.dc.entity.ApplicationRegistration;
import com.dc.entity.BankEntity;
import com.dc.entity.DcCase;
import com.dc.entity.DcChildren;
import com.dc.entity.DcEducation;
import com.dc.entity.DcIncome;
import com.dc.entity.PlanEntity;
import com.dc.repo.ApplicationRegistrationRepo;
import com.dc.repo.BankEntityRepo;
import com.dc.repo.DcCaseRepo;
import com.dc.repo.DcChildrenRepo;
import com.dc.repo.DcEducationRepo;
import com.dc.repo.DcIncomeRepo;
import com.dc.repo.PlanRepo;
import com.dc.serviceinterface.IDcService;

@Service
public class DcService  implements IDcService{

	
	private static final Logger log = LoggerFactory.getLogger(DcService.class);
	
	@Autowired
	private DcCaseRepo  caseRepo;
	
	@Autowired
	private DcIncomeRepo  incomeRepo;
	
	@Autowired
	private DcEducationRepo educationRepo;
	
	@Autowired
	private DcChildrenRepo childrenRepo;
	
	@Autowired
	private PlanRepo planRepo;
	
	@Autowired
	private ApplicationRegistrationRepo appRegRepo;
	
	@Autowired
	private BankEntityRepo bankRepo;
	
	@Override
	public Long loadCaseNum(Long appId) {
		log.info("loadCaseNum appId = "+appId);
		
		DcCase dcCase = new DcCase();
		dcCase.setAppId(appId);
		
		DcCase dcCaseSaved = caseRepo.save(dcCase);		
		
		if(dcCaseSaved.getCaseNum()!= null || dcCaseSaved.getCaseNum() != 0l)
			return dcCaseSaved.getCaseNum();
	
		return 0l;
	}

	@Override
	public PlanSelectionDto createCaseNum(Long appId) {
		log.info("createCaseId appId = " + appId);

		DcCase dcCase = new DcCase();
		dcCase.setAppId(appId);

		DcCase dcCaseSaved = caseRepo.save(dcCase);

		if (dcCaseSaved.getCaseNum() != null || dcCaseSaved.getCaseNum() != 0) {
			PlanSelectionDto planSelectionDto = new PlanSelectionDto();
			
			planSelectionDto.setPlans(findAllPlans());
			
			planSelectionDto.setCaseNum(dcCaseSaved.getCaseNum());
			return planSelectionDto;
		}

		return null;
	}

	@Override
	public Long saveIncome(IncomeDto incomeDto) {
		log.info("saveIncome");
		
		DcIncome income = new DcIncome();
		
		BeanUtils.copyProperties(incomeDto, income);
		
		DcIncome incomeSaved = incomeRepo.save(income);
		
		if(incomeSaved.getIncomeId() != null)
			return incomeSaved.getCaseNum();
		
		return (long)0;
	}

	@Override
	public Long saveEducation(EducationDto educationDto) {
		log.info("saveEducation");
		
		DcEducation education = new DcEducation();
		BeanUtils.copyProperties(educationDto, education);
		
		DcEducation educationSaved = educationRepo.save(education);
		
		if(educationSaved.getEduId() != null)
			return educationSaved.getCaseNum();
		
		return (long)0;
	}

	@Override
	public Long saveChildrens(ChildrenRequestDto childrenDto) {
		log.info("saveChildrens caseNum = "+childrenDto.getCaseNum());
		
		List<ChildrenDto> childrenDtos = childrenDto.getChildrens();
		
		for(ChildrenDto children : childrenDtos) {
			DcChildren dcChildren  = new DcChildren();
			
			BeanUtils.copyProperties(children, dcChildren);
			dcChildren.setCaseNum(childrenDto.getCaseNum());
			
			childrenRepo.save(dcChildren);
		}
		
		return childrenDto.getCaseNum();
	}

	@SuppressWarnings("deprecation")
	@Override
	public DcSummery getDCSummery(Long caseNum) {
		log.info("getDCSummery caseNum = "+caseNum);
		
		DcSummery summery = new DcSummery();
		summery.setCaseNum(caseNum);
		
		String planName = "";
		String ssn ="";
		
		/*
		 * Getting Up Plan and Applicent Details
		 */
		Optional<DcCase> ODcCase = caseRepo.findById(caseNum);
		if(ODcCase.isPresent()) {
			DcCase dcCase = ODcCase.get();
			
			Optional<PlanEntity> OPlan = planRepo.findById(dcCase.getPlanId());
			planName=OPlan.get().getPlanName();
			
			Optional<ApplicationRegistration> OAppReg = appRegRepo.findById(dcCase.getAppId());
			ssn = OAppReg.get().getSsn();	
		}
		
		/*
		 * Setting Up Income Details
		 */
		DcIncome income = incomeRepo.findByCaseNum(caseNum);
		IncomeDto incomeDto = new IncomeDto();
		BeanUtils.copyProperties(income, incomeDto);
		
		/*
		 * Setting Up Education Details
		 */
		DcEducation education = educationRepo.findByCaseNum(caseNum);
		EducationDto educationDto = new EducationDto();
		BeanUtils.copyProperties(education, educationDto);
		
		/*
		 * Getting bank Details
		 */
		
		BankEntity bankEntity = bankRepo.findByCaseNum(caseNum);
		BankDto bankDto = new BankDto();
		BeanUtils.copyProperties(bankEntity, bankDto);
		bankDto.setAccNumber(starPassword(bankDto.getAccNumber()));
		/*
		 * Setting Up Children Details
		 */
		List<DcChildren> childrens = childrenRepo.findByCaseNum(caseNum);
		List<ChildrenDto> childrenDtos = new ArrayList<>();
		
		for(DcChildren children: childrens) {
			ChildrenDto childrenDto = new ChildrenDto();
			BeanUtils.copyProperties(children, childrenDto);
			
			childrenDtos.add(childrenDto);
		}
		
		
		summery.setPlanName(planName);
		summery.setSsn(ssn);
		summery .setIncomeDto(incomeDto);
		summery.setEducationDto(educationDto);
		summery.setChildrenDtos(childrenDtos);
		
		return summery ;
	}

	@Override
	public Long savePlanToCase(PlanSelectionRequestDto planSelectionDto){
		log.info("savePlan caseNum = "+planSelectionDto.getCaseNum());
		
		DcCase dcCase = caseRepo.findByCaseNum(planSelectionDto.getCaseNum());
		
		dcCase.setPlanId(planSelectionDto.getPlanId());
		
		DcCase dcCaseSaved = caseRepo.save(dcCase);
		return dcCaseSaved.getCaseNum();
	}

	@Override
	public Map<Integer, String> findAllPlans() {
		log.info("findAllPlans");
		
		List<PlanEntity> allPlans = planRepo.findAll();
		
		HashMap<Integer, String> planMap = new HashMap<>();
		
		allPlans.forEach((plan) ->{
			planMap.put(plan.getPlanId(), plan.getPlanName());
		});
		
		return planMap;
	}

	@Override
	public Long saveBankDetails(BankDto bankDto) {
		log.info("saveBankDetails caseNum = "+bankDto.getCaseNum());
		
		BankEntity bank = new BankEntity();
		
		BeanUtils.copyProperties(bankDto,bank);
		
		BankEntity savedBank = bankRepo.save(bank);
		
		if(savedBank.getBankId() != null)
			return bankDto.getCaseNum();
		
		return 0l;
	}
	
	
	public String starPassword(String value) {
		log.info("starPassword");
		return value.replaceAll("\\w(?=\\w{4})", "*");
	}
	
	
}
