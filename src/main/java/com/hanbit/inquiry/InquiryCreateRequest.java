package com.hanbit.inquiry;

import jakarta.validation.constraints.NotBlank;

public record InquiryCreateRequest(
        String category,
        @NotBlank String content,
        String urgency,
        String summary,
        String draft) {
}
