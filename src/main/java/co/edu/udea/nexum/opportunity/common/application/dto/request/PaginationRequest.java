package co.edu.udea.nexum.profile.common.application.dto.request;

import co.edu.udea.nexum.profile.common.domain.utils.annotations.Generated;
import co.edu.udea.nexum.profile.common.domain.utils.pagination.PaginationData;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import static co.edu.udea.nexum.profile.common.domain.utils.constants.CommonDomainConstants.*;

@Data
@Builder
@AllArgsConstructor
@Generated
public class PaginationRequest implements BaseRequest {
    private Integer page;
    private String column;
    private boolean ascending;
    private Integer pageSize;

    private PaginationRequest(@Nullable PageQuery query) {
        setDefaultState();
        if(query == null) return;
        if(query.getSortBy() != null) this.column = query.getSortBy();
        if (query.getPage() != null) this.page = query.getPage();
        if (query.getAsc() != null) this.ascending = query.getAsc();
        if (query.getPageSize() != null) this.pageSize = query.getPageSize();
    }

    public static PaginationRequest build(@Nullable PageQuery query){
        return new PaginationRequest(query);
    }

    private void setDefaultState(){
        this.column = null;
        this.page = DEFAULT_PAGE_NUMBER;
        this.ascending = DEFAULT_ASCENDING;
        this.pageSize = DEFAULT_PAGE_SIZE;
    }

    public PaginationData toDomain(){
        return PaginationData.builder()
                .page(page)
                .column(column)
                .ascending(ascending)
                .pageSize(pageSize)
                .build();
    }
}