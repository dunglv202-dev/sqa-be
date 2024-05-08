package vn.edu.ptit.sqa.dto.config;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.sqa.constant.ConfigType;
import vn.edu.ptit.sqa.dto.auth.UserDTO;

import java.util.List;

@Getter
@Setter
@Builder
public class DetailConfig<T> {
    private ConfigType type;
    private UserDTO userRequested;
    private List<T> configs;
}
