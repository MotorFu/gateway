package com.motorfu.gateway.common.model;

import com.motorfu.gateway.common.base.GatewayException;
import com.motorfu.gateway.common.model.query.PageQuery;
import com.motorfu.gateway.common.utils.BeanUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author motorfu
 * @email ffu@leapcloud.cn
 * @since 2018/8/29 13:40
 */
@Setter
@Getter
@ToString
public class PageInfo<T> extends BaseEntity {
  //当前页数
  private int pageNum;

  //当前页条数
  private int pageSize;

  //总条数
  private long totalSize;

  //总页数
  private int totalPage;

  //是否存在下一页
  private boolean nextPage;

  //是否存在上一页
  private boolean prevPage;

  private List<T> list;

  public PageInfo() {
  }

  public PageInfo(PageQuery<T> query) {
    this.pageNum = query.getPageNum();
    this.pageSize = query.getPageSize();
  }

  public PageInfo(int pageNum, int pageSize, long totalSize) {
    if (pageNum <= 0) {
      throw new GatewayException("页数必须大于0");
    }
    if ((pageSize <= 0) || (pageSize > 500)) {
      throw new GatewayException("每页数据条数数必须大于0或小于500");
    }
    this.pageNum = pageNum;
    this.pageSize = pageSize;
    this.totalSize = totalSize;
    if (totalSize <= 0) {
      return;
    }
    int start = (pageNum - 1) * pageSize;
    if (start >= pageSize) {
      this.prevPage = true;
    }
    if (totalSize > (start + pageSize)) {
      this.nextPage = true;
    }
    this.totalPage = (int) Math.ceil(totalSize / 1d / pageSize);
  }

  public <DTO> PageInfo<DTO> toDTO(Class<DTO> dtoClass) {
    PageInfo<DTO> dtoPageInfo = BeanUtils.transform(PageInfo.class, this);
    List<DTO> list = this.list.stream().map(t -> BeanUtils.transform(dtoClass, t)).collect(Collectors.toList());
    dtoPageInfo.setList(list);
    return dtoPageInfo;
  }

  public <DTO> PageInfo<DTO> toDTO(FunctionDTO<DTO, T> function) {
    PageInfo<DTO> dtoPageInfo = BeanUtils.transform(PageInfo.class, this);
    List<DTO> list = this.list.stream().map(function::apply).collect(Collectors.toList());
    dtoPageInfo.setList(list);
    return dtoPageInfo;
  }

  public interface FunctionDTO<DTO, T> {
    DTO apply(T t);
  }
}
