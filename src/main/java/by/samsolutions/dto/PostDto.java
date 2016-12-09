package by.samsolutions.dto;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto implements BaseDto
{
	private String                 id;
	private String                 text;
	private String                 imageUrl;
	private String                 username;
	private String                 date;
	private Collection<CommentDto> comments;
}
