package com.jtschwartz.lynkr

object Commands {
	object Access {
		const val logout = "access|logout"
		const val lock = "access|lock"
	}
	
	object Power {
		const val shutdown = "power|shutdown"
		const val restart = "power|restart"
	}
	
	object Volume {
		const val increase = "volume|increase"
		const val decrease = "volume|decrease"
		const val mute = "volume|mute"
	}
	
	object Keystroke {
		const val alpha = "keystroke|alpha"
		const val beta = "keystroke|beta"
		const val gamma = "keystroke|gamma"
		const val delta = "keystroke|delta"
	}
}

object DeepLink {
	const val VOLUME_INCREASE = "/increase"
	const val VOLUME_DECREASE = "/decrease"
	const val MUTE = "/mute"
	const val KEYSTROKE = "/keystroke"
	const val SHUTDOWN = "/shutdown"
	const val RESTART = "/restart"
	const val LOGOUT = "/logout"
	const val LOCK = "/lock"
	
	object Params {
		const val ACTIVITY_TYPE = "keystrokeIdentifier"
		const val ALPHA = "alpha"
		const val BETA = "beta"
		const val GAMMA = "gamma"
		const val DELTA = "delta"
	}
	
	object Actions {
		const val ACTION_TOKEN_EXTRA = "actions.fulfillment.extra.ACTION_TOKEN"
	}
}