package com.project.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.payload.abstracts.BaseUserResponse06lg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter //l73
@Setter //l74
@NoArgsConstructor //l75
@SuperBuilder //l76
@JsonInclude (JsonInclude.Include.NON_NULL) //l77
public class UserResponse07lg extends BaseUserResponse06lg {
}//l72 -->l78



/*public class UserResponse07lg {
}//l72 -->l78 public class UserResponse07lg extends BaseUserResponse06lg { -->79 icin AuthenticationController01lg */
