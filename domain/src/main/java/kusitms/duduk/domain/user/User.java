package kusitms.duduk.domain.user;

import java.time.LocalDate;
import kusitms.duduk.domain.global.Category;
import kusitms.duduk.domain.global.Id;
import kusitms.duduk.domain.user.vo.Acorn;
import kusitms.duduk.domain.user.vo.Email;
import kusitms.duduk.domain.user.vo.Gender;
import kusitms.duduk.domain.user.vo.Goal;
import kusitms.duduk.domain.user.vo.Nickname;
import kusitms.duduk.domain.user.vo.Provider;
import kusitms.duduk.domain.user.vo.RefreshToken;
import kusitms.duduk.domain.user.vo.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class User {

    private Id id;
    private Email email;
    private Nickname nickname;
    private RefreshToken refreshToken;
    private Gender gender;
    private LocalDate birthday;
    private Role role;
    private Provider provider;
    private Category category;
    private Goal goal;
    private Acorn acorn;

    public void updateRefreshToken(String reIssuedRefreshToken) {
        this.refreshToken.update(reIssuedRefreshToken);
    }
}