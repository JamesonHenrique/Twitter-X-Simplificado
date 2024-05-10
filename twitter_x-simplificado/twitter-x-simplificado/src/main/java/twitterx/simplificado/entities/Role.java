package twitterx.simplificado.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long roleId;
    private String roleName;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(
            Long roleId) {
        this.roleId =
                roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(
            String roleName) {
        this.roleName =
                roleName;
    }
    public enum Values{
        ADMIN(1L),
        BASIC(2L);
        private long roleId;
        Values(long roleId){
            this.roleId = roleId;

        }
public long getRoleId(){
            return roleId;
        }
    }
}
