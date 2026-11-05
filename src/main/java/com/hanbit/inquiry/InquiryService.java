package com.hanbit.inquiry;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class InquiryService {

    private final InquiryRepository repository;

    public InquiryService(InquiryRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public InquiryResponse create(InquiryCreateRequest request) {
        Inquiry inquiry = new Inquiry(
                request.category(),
                request.content(),
                request.urgency(),
                request.summary(),
                request.draft());
        return InquiryResponse.from(repository.save(inquiry));
    }

    @Transactional(readOnly = true)
    public List<InquiryResponse> findAll(InquiryStatus status) {
        List<Inquiry> inquiries = status == null
                ? repository.findAll()
                : repository.findByStatus(status);
        return inquiries.stream().map(InquiryResponse::from).toList();
    }

    @Transactional
    public InquiryResponse approve(Long id, String editedDraft) {
        Inquiry inquiry = findInquiry(id);
        try {
            inquiry.approve(editedDraft);
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return InquiryResponse.from(inquiry);
    }

    @Transactional
    public InquiryResponse markSent(Long id) {
        Inquiry inquiry = findInquiry(id);
        try {
            inquiry.markSent();
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return InquiryResponse.from(inquiry);
    }

    private Inquiry findInquiry(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "없는 문의 번호입니다."));
    }
}
