package cz.muni.fi.pa165.smartphonEShop.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * Created by Stefan Holecko
 * Class represents:
 */
@Getter
@Setter
public class ClaimReportDTO {
    private String technicalReport;
    private Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClaimReportDTO)) return false;
        ClaimReportDTO that = (ClaimReportDTO) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
