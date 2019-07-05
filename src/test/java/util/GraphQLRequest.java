package util;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class GraphQLRequest {

	private String query;
	private Map<String, Object> variables;
	private String operationName;

	private GraphQLRequest(Builder builder) {
		this.query = builder.query;
		this.variables = builder.variables;
	}

	public static class Builder {
		private String query;
		private Map<String, Object> variables;

		public Builder(String query) {
			this.query = query;
		}

		public GraphQLRequest build() {
			return new GraphQLRequest(this);
		}

		public Builder addVariable(String name, Object value) {
			if (variables == null) {
				variables = new HashMap<>();
			}
			variables.put(name, value);
			return this;
		}
	}
}