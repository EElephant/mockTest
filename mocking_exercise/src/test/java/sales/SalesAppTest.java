package sales;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SalesAppTest {

    @Mock
    SalesReportDao salesReportDao;
    @Mock
    SalesDao salesDao;
    @Mock
    EcmService ecmService;
    @Mock
    Date date;

    @InjectMocks
    SalesApp mockSalesApp;


    @Test
    public void testGenerateReport() {

        SalesApp salesApp = new SalesApp();
        salesApp.generateSalesActivityReport("DUMMY", 1000, false, false);

    }

    @Test
    public void should_return_not_null_given_right_salesId_when_call_getSales() {
        Sales sales = new Sales();
        Mockito.when(salesDao.getSalesBySalesId(anyString())).thenReturn(sales);
		Sales result = mockSalesApp.getSales("DUMMY");
		Assert.assertNotNull(result);
    }

    @Test
    public void should_return_not_null_given_sales_when_call_getSalesReportData() {
        List<SalesReportData> reportDataList = new ArrayList<>();
        SalesReportData salesReportData = mock(SalesReportData.class);
        reportDataList.add(salesReportData);
        Sales sales = new Sales();
        Mockito.when(salesReportDao.getReportData(sales)).thenReturn(reportDataList);
        List<SalesReportData> result = mockSalesApp.getSalesReportData(false,sales,reportDataList);
        Assert.assertNotNull(result);
    }

    @Test
    public void should_run_1_tines_given_maxRow_and_reportDataList_when_call_replaceFilteredReportDataList() {
        SalesApp spySalesApp = spy(new SalesApp());
        SalesReportData salesReportData = mock(SalesReportData.class);
        List<SalesReportData> reportDataList = Arrays.asList(salesReportData);
//		List<SalesReportData>

        spySalesApp.replaceFilteredReportDataList(1000, reportDataList);

        verify(spySalesApp, times(1)).replaceFilteredReportDataList(1000, reportDataList);
    }

}
