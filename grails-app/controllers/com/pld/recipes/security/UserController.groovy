package com.pld.recipes.security

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured
import com.pld.recipes.security.Role

@Secured(['ROLE_ADMIN', 'ROLE_MASTER_USER'])
@Transactional(readOnly = true)
class UserController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond User.list(params), model:[userInstanceCount: User.count()]
    }

    def show(User userInstance) {
        respond userInstance
    }

    def create() {
        def availableRoles = Role.findAll("FROM Role AS r WHERE r.authority <> ? ORDER BY r.id", ['ROLE_ADMIN'])
        respond new User(params), model:[defaultAccessLevel: Role.findByAuthority('ROLE_GUEST_USER'), availableRoles: availableRoles]
    }

    @Transactional
    def save(User userInstance) {
        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view:'create'
            return
        }

        if (!params.accessLevel) {
            flash.error = message(code: 'user.missing.role.message')
            respond userInstance.errors, view:'edit'
            return
        }
        
        def role = Role.findByAuthority(params.accessLevel)
        if (params.accessLevel != 'ROLE_ADMIN') {
            if (!role) {
                flash.error = message(code: 'user.invalid.role.message', args:[params.accessLevel])
                respond userInstance.errors, view:'edit'
                return
            }
        }

        userInstance.save flush:true

        if (params.accessLevel != 'ROLE_ADMIN') {
            UserRole.removeAll(userInstance)
            UserRole.create userInstance, role, true
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userInstance.label', default: 'User'), userInstance.id])
                redirect userInstance
            }
            '*' { respond userInstance, [status: CREATED] }
        }
    }

    def edit(User userInstance) {
        def availableRoles = Role.findAll("FROM Role AS r WHERE r.authority <> ? ORDER BY r.id", ['ROLE_ADMIN'])
        def userRole = UserRole.findByUser(userInstance)
        def defaultAccessLevel
        if (userRole) {
            defaultAccessLevel = userRole.role
        } else {
            defaultAccessLevel = Role.findByAuthority('ROLE_GUEST_USER')
        }
        respond userInstance, model:[defaultAccessLevel: defaultAccessLevel, availableRoles: availableRoles]
    }

    @Transactional
    def update(User userInstance) {
        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view:'edit'
            return
        }
        
        if (!params.accessLevel) {
            flash.error = message(code: 'user.missing.role.message')
            respond userInstance.errors, view:'edit'
            return
        }
        
        def role = Role.findByAuthority(params.accessLevel)
        if (params.accessLevel != 'ROLE_ADMIN') {
            if (!role) {
                flash.error = message(code: 'user.invalid.role.message', args:[params.accessLevel])
                respond userInstance.errors, view:'edit'
                return
            }
        }
        
        userInstance.save flush:true

        if (params.accessLevel != 'ROLE_ADMIN') {
            UserRole.removeAll(userInstance)
            UserRole.create userInstance, role, true
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'User.label', default: 'User'), userInstance.id])
                redirect userInstance
            }
            '*'{ respond userInstance, [status: OK] }
        }
    }

    @Secured(['ROLE_ADMIN'])
    @Transactional
    def delete(User userInstance) {

        if (userInstance == null) {
            notFound()
            return
        }

        UserRole.removeAll(userInstance)
        userInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'User.label', default: 'User'), userInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userInstance.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
