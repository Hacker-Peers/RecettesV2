class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller:"recipes")
        "/index"(controller:"recipes")
        "/index/"(controller:"recipes")
        "500"(view:'/error')
	}
}
