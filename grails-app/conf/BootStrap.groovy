import com.pld.recipes.security.Role
import com.pld.recipes.security.User
import com.pld.recipes.security.UserRole
import com.pld.recipes.core.Category
import com.pld.recipes.core.Source
import com.pld.recipes.core.Recipes

class BootStrap {

    def init = { servletContext ->
        def adminRole = Role.findByAuthority('ROLE_ADMIN');
        if (!adminRole) {
            adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        }
        def masterUserRole = Role.findByAuthority('ROLE_MASTER_USER');
        if (!masterUserRole) {
            masterUserRole = new Role(authority: 'ROLE_MASTER_USER').save(flush: true)
        }
        def trustedRole = Role.findByAuthority('ROLE_TRUSTED_USER');
        if (!trustedRole) {
            trustedRole = new Role(authority: 'ROLE_TRUSTED_USER').save(flush: true)
        }
        def guestUserRole = Role.findByAuthority('ROLE_GUEST_USER');
        if (!guestUserRole) {
            guestUserRole = new Role(authority: 'ROLE_GUEST_USER').save(flush: true)
        }

        def admin = User.findByUsername('pldupont');
        if (!admin) {
            admin = new User(username: 'pldupont', password: 'admin172839', fullName: 'Pierre-Luc Dupont')
            admin.save(flush: true)
        }
        UserRole.removeAll(admin)
        UserRole.create admin, adminRole, true

        assert User.count() >= 1
        assert Role.count() == 4
        assert UserRole.count() >= 1
    }

    def destroy = {
    }
}
