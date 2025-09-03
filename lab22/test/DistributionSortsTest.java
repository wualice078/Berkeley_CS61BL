import org.junit.Assert;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class DistributionSortsTest {

    @Test
    public void testBasic() {
        int arr[] = {5,7,3,4,6,8,9,2,1,0};
        int ans[] = {0,1,2,3,4,5,6,7,8,9};
        DistributionSorts.countingSort(arr);
        assertThat(arr).isEqualTo(ans);
    }

    @Test
    public void testHard() {
        int arr[] = {5,7,3,4,6,8,9,2,1,0,3,4,2,1,5,7,6,9,8,0};
        int ans[] = {0,0,1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9};
        DistributionSorts.countingSort(arr);
        assertThat(arr).isEqualTo(ans);
    }
    
}

