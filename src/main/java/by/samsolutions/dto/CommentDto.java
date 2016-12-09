package by.samsolutions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto implements BaseDto
{
	private String id;
	private String postId;
	private String text;
	private String username;
	private String date;
}
