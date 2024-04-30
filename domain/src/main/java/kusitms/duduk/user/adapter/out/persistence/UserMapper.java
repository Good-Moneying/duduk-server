package kusitms.duduk.user.adapter.out.persistence;

import kusitms.duduk.user.domain.User;

public class UserMapper {
    public User mapToDomainEntity(UserJpaEntity userJpaEntity) {
        return User.create(userJpaEntity.getEmail(), userJpaEntity.getNickname(), userJpaEntity.getBirthday());
    }

    public UserJpaEntity mapToJpaEntity(User user) {
        return new UserJpaEntity(user.getEmail(), user.getNickname(), user.getBirthday());
    }
}
