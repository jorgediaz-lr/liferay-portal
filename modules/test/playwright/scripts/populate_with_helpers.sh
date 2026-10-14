#!/bin/bash

function main {
	set -e

	cd "$(dirname "${BASH_SOURCE[0]}")/.."

	export POPULATE_COUNT=${POPULATE_COUNT:-100}
	export POPULATE_OBJECT_PATH=${POPULATE_OBJECT_PATH:-/o/c/populatetickets}

	npx ts-node --files scripts/populate_with_helpers.ts
}

main "${@}"