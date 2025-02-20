package com.example.demo.services;

import com.github.difflib.DiffUtils;
import com.github.difflib.UnifiedDiffUtils;
import com.github.difflib.patch.Patch;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DiffService {
    public String generateUnifiedDiff(String original, String revised) {
        // Tách văn bản thành các dòng
        List<String> originalLines = Arrays.asList(original.split("\n"));
        List<String> revisedLines = Arrays.asList(revised.split("\n"));

        // Tính diff giữa 2 phiên bản
        Patch<String> patch = DiffUtils.diff(originalLines, revisedLines);

        // Tạo unified diff text
        List<String> unifiedDiff = UnifiedDiffUtils.generateUnifiedDiff("Original", "Revised", originalLines, patch, 3);
        return String.join("\n", unifiedDiff);
    }
}
