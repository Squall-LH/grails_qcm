class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller: "index", action: "index")
		"404"(controller: "error", action:'notFound')
		"500"(view:'/error')
	}
}
