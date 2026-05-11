package com.gms.backend.serviceadvisor.communication.service;

import com.gms.backend.serviceadvisor.communication.dto.CommunicationListDto;
import com.gms.backend.serviceadvisor.communication.dto.CommunicationRequestDto;
import com.gms.backend.serviceadvisor.communication.dto.DashboardResponseDto;
import com.gms.backend.serviceadvisor.communication.entity.CommunicationEntity;
import com.gms.backend.serviceadvisor.communication.entity.CustomerEntity;
import com.gms.backend.serviceadvisor.communication.entity.JobCardEntity;
import com.gms.backend.serviceadvisor.communication.repository.CommunicationRepository;
import com.gms.backend.serviceadvisor.communication.repository.CustomerRepository;
import com.gms.backend.serviceadvisor.communication.repository.JobCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunicationServiceImpl implements CommunicationService {

    private final CommunicationRepository communicationRepo;
    private final JobCardRepository jobCardRepo;
    private final CustomerRepository customerRepo;

    private void saveCommunication(Long jobCardId, String type, String message, String subject) {

        JobCardEntity jobCard = jobCardRepo.findById(jobCardId).orElseThrow();
        CustomerEntity customer = customerRepo.findById(jobCard.getCustomerId()).orElseThrow();

        CommunicationEntity comm = CommunicationEntity.builder()
                .jobCardId(jobCardId)
                .customerId(customer.getId())
                .type(type)
                .message(message)
                .subject(subject)
                .sentTo(type.equals("EMAIL") ? customer.getEmail() : customer.getPhone())
                .status("SENT")
                .createdAt(LocalDateTime.now())
                .createdBy("SYSTEM")
                .build();

        communicationRepo.save(comm);
    }

    @Override
    public void sendWhatsApp(CommunicationRequestDto dto) {
        saveCommunication(dto.getJobCardId(), "WHATSAPP", dto.getMessage(), null);
    }

    @Override
    public void sendSMS(CommunicationRequestDto dto) {
        saveCommunication(dto.getJobCardId(), "SMS", dto.getMessage(), null);
    }

    @Override
    public void sendEmail(CommunicationRequestDto dto) {
        saveCommunication(dto.getJobCardId(), "EMAIL", dto.getMessage(), dto.getSubject());
    }

    @Override
    public void logCall(CommunicationRequestDto dto) {
        saveCommunication(dto.getJobCardId(), "CALL", dto.getMessage(), null);
    }

    @Override
    public DashboardResponseDto getDashboard() {
        LocalDateTime today = LocalDateTime.now().withHour(0).withMinute(0);

        long total = communicationRepo.countByCreatedAtAfter(today);
        long whatsapp = communicationRepo.countByTypeAndCreatedAtAfter("WHATSAPP", today);
        long smsEmail = communicationRepo.countByTypeAndCreatedAtAfter("SMS", today)
                + communicationRepo.countByTypeAndCreatedAtAfter("EMAIL", today);

        return DashboardResponseDto.builder()
                .totalMessagesToday(total)
                .whatsappCount(whatsapp)
                .smsEmailCount(smsEmail)
                .build();
    }

    @Override
    public List<CommunicationListDto> getCommunicationList() {

        return communicationRepo.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(c -> {
                    JobCardEntity job = jobCardRepo.findById(c.getJobCardId()).orElseThrow();
                    CustomerEntity cust = customerRepo.findById(c.getCustomerId()).orElseThrow();


                    return CommunicationListDto.builder()
                            .jobCardId(job.getId())
                            .jobCardNumber(job.getJobCardNumber())
                            .customerName(cust.getName())
                            .status(job.getStatus())
                            .lastContact("Recently")
                            .build();
                })
                .collect(Collectors.toList());
    }
}