package com.hanbit.inquiry;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/inquiries")
public class InquiryController {

    private final InquiryService service;

    public InquiryController(InquiryService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InquiryResponse create(@Valid @RequestBody InquiryCreateRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<InquiryResponse> findAll(@RequestParam(required = false) InquiryStatus status) {
        return service.findAll(status);
    }

    @PatchMapping("/{id}/approve")
    public InquiryResponse approve(
            @PathVariable Long id,
            @RequestBody(required = false) InquiryApproveRequest request) {
        return service.approve(id, request == null ? null : request.draft());
    }

    @PatchMapping("/{id}/sent")
    public InquiryResponse markSent(@PathVariable Long id) {
        return service.markSent(id);
    }
}
