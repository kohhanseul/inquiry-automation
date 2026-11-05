package com.hanbit.inquiry;

public record InquiryResponse(
        Long id,
        String category,
        String content,
        String urgency,
        String summary,
        String draft,
        InquiryStatus status) {

    public static InquiryResponse from(Inquiry inquiry) {
        return new InquiryResponse(
                inquiry.getId(),
                inquiry.getCategory(),
                inquiry.getContent(),
                inquiry.getUrgency(),
                inquiry.getSummary(),
                inquiry.getDraft(),
                inquiry.getStatus());
    }
}
