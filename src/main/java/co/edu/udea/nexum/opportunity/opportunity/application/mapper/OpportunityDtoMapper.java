package co.edu.udea.nexum.opportunity.opportunity.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import co.edu.udea.nexum.opportunity.common.application.mapper.BaseRequestMapper;
import co.edu.udea.nexum.opportunity.common.application.mapper.BaseResponseMapper;
import co.edu.udea.nexum.opportunity.opportunity.application.dto.request.OpportunityRequestDto;
import co.edu.udea.nexum.opportunity.opportunity.application.dto.response.OpportunityResponseDto;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.BusinessContact;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.CandidateRequirements;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.Opportunity;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OpportunityDtoMapper extends BaseRequestMapper<Opportunity, OpportunityRequestDto>, BaseResponseMapper<Opportunity, OpportunityResponseDto> {

    @Override
    @Mapping(target = "businessContact", expression = "java(createBusinessContact(dto))")
    @Mapping(target = "candidateRequirements", expression = "java(createCandidateRequirements(dto))")
    Opportunity toDomain(OpportunityRequestDto dto);

    @Override
    @Mapping(target = "location", expression = "java(domain.getLocation())")
    @Mapping(target = "complementaryStudies", expression = "java(domain.getComplementaryStudies())")
    @Mapping(target = "requiredExperience", expression = "java(domain.getRequiredExperience())")
    @Mapping(target = "travelAvailability", expression = "java(domain.getTravelAvailability())")
    @Mapping(target = "workModality", expression = "java(domain.getWorkModality())")
    @Mapping(target = "businessName", expression = "java(domain.getBusinessName())")
    @Mapping(target = "contactName", expression = "java(domain.getContactName())")
    @Mapping(target = "businessEmail", expression = "java(domain.getBusinessEmail())")
    @Mapping(target = "businessPhone", expression = "java(domain.getBusinessPhone())")
    OpportunityResponseDto toResponse(Opportunity domain);

    default BusinessContact createBusinessContact(OpportunityRequestDto dto) {
        if (dto.getBusinessName() == null && dto.getContactName() == null &&
            dto.getBusinessEmail() == null && dto.getBusinessPhone() == null) {
            return null;
        }
        return BusinessContact.builder()
                .businessName(dto.getBusinessName())
                .contactName(dto.getContactName())
                .businessEmail(dto.getBusinessEmail())
                .businessPhone(dto.getBusinessPhone())
                .build();
    }

    default CandidateRequirements createCandidateRequirements(OpportunityRequestDto dto) {
        return CandidateRequirements.builder()
                .complementaryStudies(dto.getComplementaryStudies())
                .requiredExperience(dto.getRequiredExperience())
                .location(dto.getLocation())
                .travelAvailability(dto.getTravelAvailability())
                .workModality(dto.getWorkModality())
                .coursedProgramIds(dto.getCoursedProgramIds())
                .programCompetencyIds(dto.getProgramCompetencyIds())
                .jobAreaIds(dto.getJobAreaIds())
                .build();
    }
}