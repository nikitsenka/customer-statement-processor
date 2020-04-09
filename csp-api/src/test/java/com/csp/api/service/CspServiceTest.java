package com.csp.api.service;

import com.csp.api.domain.entity.Record;
import com.csp.api.domain.entity.Report;
import com.csp.api.domain.repository.ReportRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.csp.api.service.CspService.DUPLICATION_ERROR;
import static com.csp.api.service.CspService.INVALID_END_BALANCE;
import static com.csp.api.service.CspService.SUCCESS;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CspServiceTest {

    @Mock
    private ReportRepository repository;
    @Captor
    private ArgumentCaptor<Report> reportArgumentCaptor;
    @InjectMocks
    private CspService cspService;


    @Test
    public void shouldProcessSuccessIfValidRecord() {
        //Given
        Record record = new Record(1L, "acc1", "10.0", "-5.3", "descr", "4.7");
        //When
        cspService.process(List.of(record));
        //Then
        verify(repository).save(reportArgumentCaptor.capture());
        Report result = reportArgumentCaptor.getValue();
        assertEquals(1, result.getResults().size());
        assertEquals(SUCCESS, result.getResults().get(0).getCode().intValue());
    }

    @Test
    public void shouldReportErrorIfDuplicatedRecord() {
        //Given
        Record record1 = new Record(1L);
        Record record2 = new Record(1L);
        //When
        cspService.process(List.of(record1, record2));
        //Then
        verify(repository).save(reportArgumentCaptor.capture());
        Report result = reportArgumentCaptor.getValue();
        assertEquals(2, result.getResults().size());
        assertEquals(DUPLICATION_ERROR, result.getResults().get(0).getCode().intValue());
        assertEquals(DUPLICATION_ERROR, result.getResults().get(1).getCode().intValue());
    }

    @Test
    public void shouldReportErrorIfInvalidBalanceRecord() {
        //Given
        Record record1 = new Record(1L, "acc1", "10.0", "-5.3", "descr", "4.7");
        Record record2 = new Record(2L, "acc1", "10.0", "-5.3", "descr", "0");
        //When
        cspService.process(List.of(record1, record2));
        //Then
        verify(repository).save(reportArgumentCaptor.capture());
        Report result = reportArgumentCaptor.getValue();
        assertEquals(2, result.getResults().size());
        assertEquals(SUCCESS, result.getResults().get(0).getCode().intValue());
        assertEquals(INVALID_END_BALANCE, result.getResults().get(1).getCode().intValue());
    }
}
