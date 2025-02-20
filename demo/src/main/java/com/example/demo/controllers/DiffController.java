package com.example.demo.controllers;

import com.example.demo.services.DiffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiffController {
    @Autowired
    private DiffService diffService;

    /**
     * Endpoint trả về unified diff giữa 2 phiên bản của đơn hàng.
     * Ví dụ sử dụng dữ liệu cố định để demo.
     */
    @GetMapping("/api/diff")
    public String getDiff() {
        String original = "OrderID: 1001\nCustomer: Nguyen Van A\nTotal: 500.00\nStatus: Pending";
        String revised = "OrderID: 1001\nGuest:Nguyen Van B\nTotal: 550.00\nStatus: Confirmed";
        return diffService.generateUnifiedDiff(original, revised);
    }
}
